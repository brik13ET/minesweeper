package tp.minesweeper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MinesweeperApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
	}

	@Test
	void hasAdmin() throws Exception {
		this.mockMvc.perform(get("/isUserExists?id=0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	void staticHostIndex() throws Exception {
		this.mockMvc.perform(get("/index.html"))
				.andDo(print())
				.andExpect(status().isOk());
	}


	@Test
	void coffee_tea() throws Exception {
		this.mockMvc.perform(get("/make_coffee"))
				.andDo(print())
				.andExpect(status().isIAmATeapot());
	}

	@Test
	void staticRedirectIndex() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(redirectedUrl("/reg.html"));
	}
	// Post /newGameField
	@Test
	void newGameField () throws Exception {
		this.mockMvc.perform(post("/newGameField"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Post /newUser
	@Test
	void newUser () throws Exception {
		this.mockMvc.perform(post("/newUser"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Post /newUserField
	@Test
	void newUserField () throws Exception {
		this.mockMvc.perform(post("/newUserField"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /isUserExists
	@Test
	void isUserExists () throws Exception {
		this.mockMvc.perform(get("/isUserExists?id=0"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /isFieldExists
	@Test
	void isFieldExists () throws Exception {
		this.mockMvc.perform(get("/isFieldExists?id=0"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /isCorrectPass
	@Test
	void isCorrectPass () throws Exception {
		this.mockMvc.perform(get("/isCorrectPass"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /make_coffee
	@Test
	void make_coffee () throws Exception {
		this.mockMvc.perform(get("/make_coffee"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /getGameMapsIds
	@Test
	void getGameMapsIds () throws Exception {
		this.mockMvc.perform(get("/getGameMapsIds"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /getUserSaves
	@Test
	void getUserSaves () throws Exception {
		this.mockMvc.perform(get("/getUserSaves"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /getUserCell
	@Test
	void getUserCell () throws Exception {
		this.mockMvc.perform(get("/getUserCell"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /getUserId
	@Test
	void getUserId () throws Exception {
		this.mockMvc.perform(get("/getUserId"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	// Get /getGameFieldParams
	@Test
	void getGameFieldParams () throws Exception {
		this.mockMvc.perform(get("/getGameFieldParams"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
