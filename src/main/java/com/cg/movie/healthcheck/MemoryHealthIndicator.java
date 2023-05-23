package com.cg.movie.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class MemoryHealthIndicator implements HealthIndicator {

	/*
	 * A custom health check indicator that checks the amount of free memory in the
	 * JVM.
	 */

	@Override
	public Health health() {
		long freeMemory = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		double freeMemoryPercent = ((double) freeMemory / (double) totalMemory) * 100;

		if (freeMemoryPercent > 25) {
			return Health.up()
					.withDetail("free_memory", freeMemory + " bytes")
					.withDetail("total_memory", totalMemory + " bytes")
					.withDetail("free_memory_percent", freeMemoryPercent + "%")
					.build();
		} else {
			return Health.down()
					.withDetail("free_memory", freeMemory + " bytes")
					.withDetail("total_memory", totalMemory + " bytes")
					.withDetail("free_memory_percent", freeMemoryPercent + "%")
					.build();
		}
	}

}
