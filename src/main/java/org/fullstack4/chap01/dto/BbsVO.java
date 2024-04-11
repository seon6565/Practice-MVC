package org.fullstack4.chap01.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BbsVO {
    private int idx;
    private String user_id;
    private String title;
    private String content;
    private String display_date;
    private LocalDate reg_date;
    private LocalDate modify_date;
    private int read_cnt;

}
