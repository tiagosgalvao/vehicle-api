package com.galvao.vehicle.service.impl;

import com.galvao.vehicle.mapper.impl.VehicleMapper;
import com.galvao.vehicle.model.entity.impl.Model;
import com.galvao.vehicle.repository.impl.ModelRepository;
import com.galvao.vehicle.repository.impl.VehicleRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static com.galvao.vehicle.model.enums.Status.ACTIVE;
import static org.apache.commons.codec.Charsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
	private static final String SAMPLE_VEHICLE_LINE = "1,VND,2011,true,true,true";
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	private ModelRepository modelRepository;
	private VehicleService vehicleService;

	@Before
	public void setUp() {
		this.modelRepository = mock(ModelRepository.class);
		VehicleRepository vehicleRepository = mock(VehicleRepository.class);
		VehicleMapper mapper = mock(VehicleMapper.class);
		this.vehicleService = new VehicleService(vehicleRepository, mapper, modelRepository);
	}

	@Test
	public void shouldFailBatchModeWrongDataFormat() throws IOException {
		MultipartFile file = getWrongTestFile();
		vehicleService.batchCreate(file);
	}

	@Test
	public void shouldCreateInBatchMode() throws IOException {
		Model model = Model.builder().id(1L).name("Model").build();
		when(modelRepository.findOneByIdAndStatus(1L, ACTIVE)).thenReturn(Optional.of(model));

		MultipartFile file = getTestFile();
		assertThatCode(() -> vehicleService.batchCreate(file)).doesNotThrowAnyException();
	}

	@Test
	public void shouldFailWhenCreatingInBatchMode() {
		expectedException.expect(AssertionError.class);
		expectedException.expectMessage("Expecting code not to raise a throwable but caught");
		assertThatCode(() -> vehicleService.batchCreate(null)).doesNotThrowAnyException();
	}

	public MultipartFile getTestFile() throws IOException {
		InputStream stream = IOUtils.toInputStream(SAMPLE_VEHICLE_LINE, UTF_8);
		return new MockMultipartFile("mock_file.csv", stream);
	}

	public MultipartFile getWrongTestFile() throws IOException {
		InputStream stream = IOUtils.toInputStream("WRONG_DATA", UTF_8);
		return new MockMultipartFile("mock_file.csv", stream);
	}

}