package rag.mcp.mcp_server.tools;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.ai.mcp.annotation.McpTool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTool {

    private static final Logger log = LoggerFactory.getLogger(DateTimeTool.class);

    public record DateTimeRequest(
            String timeZone
    ){}

    public record DateTimeResponse(
            String dayOfWeek,
            String date,
            String time
    ) {}

    @Tool(
            name = "getDateTime",
            description = "Get the current date and time for the specified timezone"
    )
    public DateTimeResponse getDateTime(DateTimeRequest dateTimeRequest) {

        log.info("========== DATE TIME TOOL START ==========");

        ZoneId zoneId = ZoneId.of(dateTimeRequest.timeZone());

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        String dayOfWeek = zonedDateTime
                .getDayOfWeek()
                .toString();

        String date = zonedDateTime
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String time = zonedDateTime
                .toLocalTime()
                .format(DateTimeFormatter.ofPattern("HH:mm a"));

        log.info(
                "getDateTime called: timezone={}, {} {} {}",
                dateTimeRequest.timeZone(),
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
