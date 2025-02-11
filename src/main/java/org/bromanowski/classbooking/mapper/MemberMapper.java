package org.bromanowski.classbooking.mapper;

import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.MemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member member);

    Member toEntity(MemberDto memberDto);
}
