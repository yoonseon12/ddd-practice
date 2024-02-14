package dev.practice.order.interfases.item;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {
	// register
	@Mapping(source = "request.itemOptionGroupList", target = "itemOptionGroupRequestList")
	ItemCommand.RegisterItemRequest of(ItemDto.RegisterItemRequest request);

	@Mapping(source = "itemOptionList", target = "itemOptionRequestList")
	ItemCommand.RegisterItemOptionGroupRequest of(ItemDto.RegisterItemOptionGroupRequest request);

	ItemCommand.RegisterItemOptionRequest of(ItemDto.RegisterItemOptionRequest request);

	ItemDto.RegisterResponse of(String itemToken);

	// retrieve
	ItemDto.Main of(ItemInfo.Main main);

	ItemDto.ItemOptionGroupInfo of(ItemInfo.ItemOptionGroupInfo itemOptionGroup);

	ItemDto.ItemOptionInfo of(ItemInfo.ItemOptionInfo itemOption);
}
