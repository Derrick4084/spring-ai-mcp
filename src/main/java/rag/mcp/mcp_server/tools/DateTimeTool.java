package rag.mcp.mcp_server.tools;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.ai.mcp.annotation.McpTool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTool {

    private static final Logger log = LoggerFactory.getLogger(DateTimeTool.class);

    public record DateTimeRequest(
            String request
    ){}

    public record DateTimeResponse(
            String dayOfWeek,
            String date,
            String time
    ) {}

    @Tool(
            name = "getDateTime",
            description = "Get the current date and time"
    )
    public DateTimeResponse getDateTime(DateTimeRequest dateTimeRequest) {

            log.info("========== DATE TIME TOOL START ==========");

            LocalDateTime now = LocalDateTime.now();

            String dayOfWeek = now.getDayOfWeek().toString();
            String date = now.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String time = now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            log.info(
                    "getDateTime called: {} {} {}",
                    dayOfWeek,
                    date,
                    time
            );

            DateTimeResponse response = new DateTimeResponse(
                    dayOfWeek,
                    date,
                    time
            );

            log.info("========== DATE TIME TOOL END ==========");

            return response;
        }

}
