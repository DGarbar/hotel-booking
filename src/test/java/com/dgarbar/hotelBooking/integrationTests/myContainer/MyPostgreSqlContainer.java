package com.dgarbar.hotelBooking.integrationTests.myContainer;

import org.testcontainers.containers.PostgreSQLContainer;

public class MyPostgreSqlContainer extends PostgreSQLContainer<MyPostgreSqlContainer> {

	private static final String IMAGE_VERSION = "postgres:11.1";
	private static MyPostgreSqlContainer container;

	private MyPostgreSqlContainer() {
		super(IMAGE_VERSION);
	}

	public static MyPostgreSqlContainer getInstance() {
		if (container == null) {
			container = new MyPostgreSqlContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("DB_URL", container.getJdbcUrl());
		System.setProperty("DB_USERNAME", container.getUsername());
		System.setProperty("DB_PASSWORD", container.getPassword());
	}

	@Override
	public void stop() {
		//do nothing, JVM handles shut down
	}
}
