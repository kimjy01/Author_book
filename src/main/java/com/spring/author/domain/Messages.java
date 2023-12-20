package com.spring.author.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messages {
	
	public enum MessageType{
		ENTER, TALK, LEAVE;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;
	
	@Column(name = "type")
	private MessageType type;
	
	@ManyToOne
    @JoinColumn(name = "senderId")
    private Users sender;

    @Column(name = "message")
    private String message;
	
	@CreatedDate
	@Column(name = "sendTime")
    private LocalDateTime sendTime;
    
    @ManyToOne
    @JoinColumn(name = "chatRoomId")
    private ChatRooms chatRoom;
}
