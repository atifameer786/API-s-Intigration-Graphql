package org.task;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Information")
@NamedQuery(name = "Information.findAll", query = "SELECT f FROM Information f ORDER BY f.id", 
            hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection(serialization = true)
public class Information {
    @Id
    private int id;
    @Column
    private String name;  
}
