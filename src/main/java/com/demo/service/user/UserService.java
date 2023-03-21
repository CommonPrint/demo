package com.demo.service.user;

import java.util.List;
import java.util.Optional;

import com.demo.dto.createedit.user.UserCreateEditDto;
import com.demo.dto.read.user.UserReadDto;
import com.demo.mapper.createedit.user.UserCreateEditMapper;
import com.demo.mapper.read.user.UserReadMapper;
import org.springframework.stereotype.Service;

import com.demo.repository.user.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserCreateEditMapper userCreateEditMapper;
	private final UserReadMapper userReadMapper;


	//	Получить всех пользователей
	public List<UserReadDto> findAllUsers() {
		return this.userRepository.findAll()
				.stream()
				.map(userReadMapper::map)
				.toList();

	}//findAll()


	//	Получить конкретного Пользователя
	public Optional<UserReadDto> findById(Long id) {

		return userRepository.findById(id)
				.map(userReadMapper::map);

	}//findById(Long id)


	// Создать Пользователя
	public UserReadDto create(UserCreateEditDto userDto) {
		return Optional.of(userDto)
					.map(dto -> {
						return userCreateEditMapper.map(dto);
					})
					.map(userRepository::save)
					.map(userReadMapper::map)
				.orElseThrow();

	}//create(UserCreateEditDto userDto)


	// Обновить Пользователя
	public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {

		return userRepository.findById(id)
				.map(entity -> {
					return userCreateEditMapper.map(userDto, entity);
				})
				.map(userRepository::saveAndFlush)
				.map(userReadMapper::map);

	}//update(Long id, UserCreateEditDto userDto)



	// Удалить Пользователя
	public boolean delete(Long id) {

		return userRepository.findById(id)
				.map(entity -> {
					userRepository.delete(entity);
					userRepository.flush();

					return true;
				})
				.orElse(false);

	}
	
}
