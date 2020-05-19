package com.edu.college.common.page;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    @Builder.Default
    private List<T> data = Lists.newArrayList();

    @Builder.Default
    private  long total = 0;

}
