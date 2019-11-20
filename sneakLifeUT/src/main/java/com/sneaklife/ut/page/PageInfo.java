package com.sneaklife.ut.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

	private Integer page;

	private Integer rows;

	private String sort;

	private String sortOrder;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"page\":")
				.append(page);
		sb.append(",\"rows\":")
				.append(rows);
		sb.append(",\"sort\":\"")
				.append(sort).append('\"');
		sb.append(",\"sortOrder\":\"")
				.append(sortOrder).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
