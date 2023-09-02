package com.ij34.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p >
 *
 * @author sunday123
 * @since 2023-02-23
 */
@Data
@Accessors(chain = true)
public class HelloDto implements Serializable {
    private Integer id;
    private String  name;
    private Date createDate;
}
