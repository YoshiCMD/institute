package com.codecmd.institute;

import com.codecmd.institute.shared.util.DefaultProfileUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		DefaultProfileUtil.addDefaultProfile(application.application());
		return application.sources(InstituteApplication.class);
	}
}
