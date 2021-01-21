package com.example.demo.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class MethodUtils {

	public static Pageable getPageable(Integer pageNumber, Integer noOfRecords, String sortColumn, String sortOrder,
			String searchTest) {

		PageRequest pageable = PageRequest.of(pageNumber, noOfRecords);

		if (sortOrder != null && sortColumn != null) {

			Direction direction = null;
			if (sortOrder.equals("DESC")) {
				direction = Sort.Direction.DESC;
			} else {
				direction = Sort.Direction.ASC;
			}
			pageable = PageRequest.of(pageNumber, noOfRecords, direction, sortColumn);
		}

		return pageable;

	}

}
