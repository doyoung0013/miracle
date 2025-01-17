package com.green.miracle.domain.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.green.miracle.domain.dto.BoardDetailDTO;
import com.green.miracle.domain.dto.BoardUpdateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "board")
public class BoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardNo; // 글번호 (pk)

	@ManyToOne
	@JoinColumn(name = "empNo", nullable = false)
	private EmployeeEntity employee; // 사원번호 (fk)

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String boardTitle; //글제목
	
	@Column(columnDefinition = "text", nullable = false)
	private String boardContent; //글내용
	
	@Column(columnDefinition = "bigint")
	@ColumnDefault("0")
	private long boardComment; //댓글수
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp", nullable = false)
	private LocalDate writeAt; // 작성일

	public BoardDetailDTO toBoardDetailDTO() {
		return BoardDetailDTO.builder().boardNo(boardNo).employee(employee).boardTitle(boardTitle)
				.boardContent(boardContent).writeAt(writeAt).build();
	}

	public BoardEntity update(BoardUpdateDTO dto) {
		this.boardTitle = dto.getBoardTitle();
		this.boardContent = dto.getBoardContent();
		return this;

	}

}