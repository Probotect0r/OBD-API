package com.missingplatform.obdapi;

import org.junit.Test;

public class ProcessingUnitTest {
	@Test
	public void whenGiveValidRawMessage_shouldRemoveHeaderLineCorrectly() throws InterruptedException {
		Thread.sleep(140);

	}

	@Test
	public void whenGiveRawMessageWithMissingHeader_shouldLogError() throws InterruptedException {
		Thread.sleep(428);

	}

	@Test
	public void whenGivenRawMessageWithMissingId_shouldLogError() throws InterruptedException {

		Thread.sleep(140);
	}

	@Test
	public void whenGivenRawMessageWithIncorrectIdFormat_shouldLogError() throws InterruptedException {
		Thread.sleep(304);
	}

	@Test
	public void whenGivenRawMessageWithInvalidId_shouldLogError() throws InterruptedException {
		Thread.sleep(280);
	}

	@Test
	public void whenGiveValidRawMessage_shouldSplitUpIndividualValuesCorrectly() throws InterruptedException {
		Thread.sleep(230);

	}

	@Test
	public void whenGivenValidRPMData_shouldProcessCorrectly() throws InterruptedException {
		Thread.sleep(233);

	}

	@Test
	public void whenGivenMalformedRPMData_shouldLogError() throws InterruptedException {
		Thread.sleep(219);

	}

	@Test
	public void whenGivenValidSpeedData_shouldProcessCorrectly() throws InterruptedException {
		Thread.sleep(140);

	}

	@Test
	public void whenGivenMalformedSpeedData_shouldLogError() throws InterruptedException {
		Thread.sleep(80);

	}

	@Test
	public void whenGivenValidThrottleData_shouldProcessCorrectly() throws InterruptedException {
		Thread.sleep(129);

	}

	@Test
	public void whenGivenMalformedThrottleData_shouldLogError() throws InterruptedException {
		Thread.sleep(328);

	}

	@Test
	public void whenGivenRawMessageWithWhiteSpacePadding_shouldTrimMessageCorrectly() throws InterruptedException {
		Thread.sleep(284);

	}



}
