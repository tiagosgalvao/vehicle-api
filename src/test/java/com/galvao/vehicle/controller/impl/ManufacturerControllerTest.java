package com.galvao.vehicle.controller.impl;

import com.galvao.vehicle.config.DataSourceConfiguration;
import com.galvao.vehicle.controller.BaseControllerTest;
import com.galvao.vehicle.mapper.impl.ManufacturerMapper;
import com.galvao.vehicle.model.dto.impl.ManufacturerDto;
import com.galvao.vehicle.model.entity.impl.Manufacturer;
import com.galvao.vehicle.repository.impl.ManufacturerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = DataSourceConfiguration.Initializer.class)
@SpringJUnitConfig
@ActiveProfiles("TEST")
public class ManufacturerControllerTest extends BaseControllerTest<Manufacturer, ManufacturerDto, ManufacturerRepository, ManufacturerMapper> {
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	@Autowired
	private ManufacturerMapper manufacturerMapper;
	@Autowired
	private MockMvc mockMvc;
	private int i = 0;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@After
	public void afterEach() {
		manufacturerRepository.deleteAll();
	}

	@Before
	public void beforeEach() {
		rootUrl = "/v1/manufacturers/";
		manufacturerRepository.deleteAll();
	}

	@Override
	public Manufacturer createRandomEntity() {
		i++;
		return Manufacturer.builder().name("Manufacturer" + i).build();
	}

	@WithMockUser("spring")
	@Test
	public void shouldCreate() throws Exception {
		Manufacturer manufacturer = createRandomEntity();
		manufacturer = manufacturerRepository.save(manufacturer);
		ManufacturerDto manufacturerDto = mapper.entityToDto(manufacturer);

		mockMvc.perform(post(rootUrl).with(csrf()).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(manufacturerDto)))
				.andDo(print())
				.andExpect(status().isCreated());

		mockMvc.perform(get(rootUrl + "/" + manufacturer.getId()))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.id").value(manufacturer.getId().intValue()));
	}

	@WithMockUser("spring")
	@Test
	public void shouldUpdateById() throws Exception {
		Manufacturer manufacturer = createRandomEntity();
		manufacturer = manufacturerRepository.save(manufacturer);
		ManufacturerDto request = manufacturerMapper.entityToDto(manufacturer);
		request.setName("Manufacturer ETC");

		mockMvc.perform(put(rootUrl + "/" + manufacturer.getId()).with(csrf())
				.contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isOk());

		mockMvc.perform(get(rootUrl + "/" + manufacturer.getId()))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.name").value(request.getName()));
	}

	@WithMockUser("spring")
	@Test
	public void shouldDeleteById() throws Exception {
		Manufacturer entity1 = createRandomEntity();
		Manufacturer entity2 = createRandomEntity();
		repository.save(entity1);
		repository.save(entity2);

		mockMvc.perform(delete(rootUrl + "/" + entity1.getId()).with(csrf()))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(rootUrl))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.content", hasSize(1)));
	}

	@WithMockUser("spring")
	@Test
	public void shouldFindAll() throws Exception {
		Manufacturer entity1 = createRandomEntity();
		Manufacturer entity2 = createRandomEntity();
		repository.save(entity1);
		repository.save(entity2);

		mockMvc.perform(get(rootUrl))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[*].id", containsInAnyOrder(entity1.getId().intValue(), entity2.getId().intValue())));
	}

	@WithMockUser("spring")
	@Test
	public void shouldFindOneById() throws Exception {
		Manufacturer entity1 = createRandomEntity();
		Manufacturer entity2 = createRandomEntity();
		repository.save(entity1);
		repository.save(entity2);

		mockMvc.perform(get(rootUrl + "/" + entity1.getId()))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.id").value(entity1.getId().intValue()));
	}

}