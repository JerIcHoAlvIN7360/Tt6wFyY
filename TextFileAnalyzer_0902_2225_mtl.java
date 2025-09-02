// 代码生成时间: 2025-09-02 22:25:07
package com.example;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.ConfigMappingProvider;
import io.smallrye.config.ConfigProperties;
import io.smallrye.config.WithDefault;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/analyze")
public class TextFileAnalyzer {

    @Inject
    TextFileAnalyzerConfig config;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> analyzeFile(byte[] fileContent) {
        try {
            Path tempFile = Files.createTempFile("text-", ".txt");
            Files.write(tempFile, fileContent);
            AnalyzeResult result = analyzeContent(Files.readAllLines(tempFile));
            Files.delete(tempFile);
            return CompletableFuture.completedFuture(Response.ok(result).build());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(new BadRequestException("Failed to analyze file content", e));
        }
    }

    @GET
    @Path("/count/{fileId}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnalyzeResult analyzeFileById(@PathParam("fileId") String fileId) {
        try {
            Path filePath = Paths.get(config.getTextFilePath()).resolve(fileId);
            if (Files.notExists(filePath)) {
                throw new BadRequestException("File not found");
            }
            return analyzeContent(Files.readAllLines(filePath));
        } catch (Exception e) {
            throw new BadRequestException("Failed to analyze file", e);
        }
    }

    private AnalyzeResult analyzeContent(List<String> lines) {
        // Implement content analysis logic here
        int wordCount = lines.stream().map(String::trim).filter(s -> !s.isEmpty()).count();
        return new AnalyzeResult(wordCount);
    }

    @ConfigMapping(prefix = "textfileanalyzer")
    @ConfigProperties
    public interface TextFileAnalyzerConfig {
        String getTextFilePath();

        // Additional configuration properties can be added here
    }

    public static class AnalyzeResult {
        private int wordCount;

        public AnalyzeResult(int wordCount) {
            this.wordCount = wordCount;
        }

        public int getWordCount() {
            return wordCount;
        }
    }
}