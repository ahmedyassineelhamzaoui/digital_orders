package com.app.commons.exceptions.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class ErrorResponseFormat {

	  private  LocalDateTime timestamp;
	  private  String message;
	  private  String path;
	  private  Map<String, List<String>> details;

}
