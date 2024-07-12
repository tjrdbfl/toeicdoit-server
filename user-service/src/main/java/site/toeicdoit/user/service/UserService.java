package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.model.mysql.UserModel;
import site.toeicdoit.user.domain.vo.Messenger;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {

    default UserModel dtoToEntity(UserDto dto){
        return UserModel.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .profile(dto.getProfile())
                .name(dto.getName())
                .phone(dto.getPhone())
                .toeicLevel(dto.getToeicLevel())
                .registration(dto.getRegistration())
                .oauthId(dto.getOauthId())
                .build();
    }

    default UserDto entityToDto(UserModel userModel){
        return UserDto.builder()
                .id(userModel.getId())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .profile(userModel.getProfile())
                .name(userModel.getName())
                .phone(userModel.getPhone())
                .toeicLevel(userModel.getToeicLevel())
                .registration(userModel.getRegistration())
                .role(userModel.getRoleIds().toString())
                .calendarId(userModel.getCalendarId().getId())
                .oauthId(userModel.getOauthId())
                .createdAt(userModel.getCreatedAt())
                .updatedAt(userModel.getUpdatedAt())
                .build();
    }

    Messenger count();
    Messenger login(UserDto dto);
    Messenger existsByEmail(String email);
    Messenger oauthJoin(UserDto dto);
}
