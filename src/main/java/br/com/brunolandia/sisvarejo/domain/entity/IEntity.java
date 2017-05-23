//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.brunolandia.sisvarejo.domain.entity;

import java.io.Serializable;

public interface IEntity<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID var1);
}
