package org.bromanowski.classbooking.model.mapper;

import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.dto.MemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member member);

    Member toEntity(MemberDto memberDto);
}
