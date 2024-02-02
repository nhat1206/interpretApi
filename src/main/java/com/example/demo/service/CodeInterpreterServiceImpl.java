// CodeInterpreterServiceImpl.java
package com.example.demo.service;

import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CodeInterpreterServiceImpl implements CodeInterpreterService {

    @Override
    public String interpretCode(String code) {
        // Remove double quotes from the provided code
        code = code.replaceAll("\"", "");

        try {
            // Creating a temporary class name
            String className = "DynamicClass";

            // Creating the Java source file
            String javaCode = "import java.util.*; public class " + className +
                    " { public static void main(String[] args) { " +
                    code + " System.out.println(result); } }";

            // Compile the Java code
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            Path sourceFile = createSourceFile(javaCode, className);

            // Compile the source file
            int compilationResult = compiler.run(null, null, null, sourceFile.toString());

            if (compilationResult != 0) {
                return "Compilation failed.";
            }

            // Run the compiled class
            Process process = Runtime.getRuntime().exec("java -cp " + sourceFile.getParent() + " " + className);
            String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
            return "Output: " + output;

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    private Path createSourceFile(String code, String className) throws IOException {
        Path sourcePath = Files.createTempDirectory("tempJavaSource");
        Path sourceFile = sourcePath.resolve(className + ".java");

        code = code.replace("\\n", "\n");
        Files.write(sourceFile, Arrays.asList(code.split("\n")));
        return sourceFile;
    }
}
