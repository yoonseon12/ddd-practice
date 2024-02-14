package dev.practice.order.interfases.item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.practice.order.application.item.ItemFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemApiController {
	private final ItemFacade itemFacade;
	private final ItemDtoMapper itemDtoMapper;

	@PostMapping
	public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
		String partnerToken = request.getPartnerToken();
		ItemCommand.RegisterItemRequest itemCommand = itemDtoMapper.of(request);
		String itemToken = itemFacade.registerItem(itemCommand, partnerToken);
		ItemDto.RegisterResponse response = itemDtoMapper.of(itemToken);

		return CommonResponse.success(response);
	}

	@PostMapping("/change-on-sales")
	public CommonResponse changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
		String itemToken = request.getItemToken();
		itemFacade.changeOnSalesItem(itemToken);

		return CommonResponse.success();
	}

	@PostMapping("/change-end-of-sales")
	public CommonResponse changeEndOfSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
		String itemToken = request.getItemToken();
		itemFacade.changeEndOfSaleItem(itemToken);

		return CommonResponse.success();
	}

	@GetMapping("/{itemToken}")
	public CommonResponse retrieve(@PathVariable("itemToken") String itemToken) {
		ItemInfo.Main itemInfo = itemFacade.retrieveItemInfo(itemToken);
		ItemDto.Main response = itemDtoMapper.of(itemInfo);
		return CommonResponse.success(response);
	}
}
