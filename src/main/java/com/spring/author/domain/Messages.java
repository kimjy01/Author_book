package com.spring.author.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Messages {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;

	@Column(name = "content")
    private String content;
	
	@CreatedDate
	@Column(name = "send_time")
    private LocalDateTime send_time;

    @ManyToOne
    @JoinColumn(name = "chat_room_user_id")
    private ChatRoomUsers chat_room_user;
    
    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRooms chatRoom;
}
