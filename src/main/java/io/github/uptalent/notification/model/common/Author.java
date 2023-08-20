package io.github.uptalent.notification.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {
    private long id;
    private String name;
    private String avatar;
}
