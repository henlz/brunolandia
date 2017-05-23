//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.brunolandia.sisvarejo.domain.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntity implements IEntity<Long> {
    private static final long serialVersionUID = -3875941859616104733L;
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    protected Long id;
    @Column(
        nullable = false,
        updatable = false
    )
    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar created;
    @Temporal(TemporalType.TIMESTAMP)
    protected Calendar updated;

    public AbstractEntity() {
    }

    public AbstractEntity(Long id) {
        this.setId(id);
    }

    public int hashCode() {
        boolean prime = true;
        int result = 1;
        result = 31 * result + (this.created == null?0:this.created.hashCode());
        result = 31 * result + (this.id == null?0:this.id.hashCode());
        result = 31 * result + (this.updated == null?0:this.updated.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            AbstractEntity other = (AbstractEntity)obj;
            if(this.created == null) {
                if(other.created != null) {
                    return false;
                }
            } else if(!this.created.equals(other.created)) {
                return false;
            }

            if(this.id == null) {
                if(other.id != null) {
                    return false;
                }
            } else if(!this.id.equals(other.id)) {
                return false;
            }

            if(this.updated == null) {
                if(other.updated != null) {
                    return false;
                }
            } else if(!this.updated.equals(other.updated)) {
                return false;
            }

            return true;
        }
    }

    @PrePersist
    protected void refreshCreated() {
        if(this.created == null) {
            this.created = Calendar.getInstance();
        }

    }

    @PreUpdate
    protected void refreshUpdated() {
        this.refreshCreated();
        this.updated = Calendar.getInstance();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return this.created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return this.updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }
}
