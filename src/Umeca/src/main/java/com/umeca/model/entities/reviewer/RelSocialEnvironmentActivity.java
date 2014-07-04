package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Activity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 06:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="rel_social_environment_activity")
public class RelSocialEnvironmentActivity {

    @Id
    @GeneratedValue
    @Column(name="id_rel")
    private Long relId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_activity", nullable = false)
    private Activity activity;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_social_environment", nullable = false)
    private SocialEnvironment socialEnvironment;

    @Column(name="specification", length = 255, nullable = true)
    private String specification;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public SocialEnvironment getSocialEnvironment() {
        return socialEnvironment;
    }

    public void setSocialEnvironment(SocialEnvironment socialEnvironment) {
        this.socialEnvironment = socialEnvironment;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }
}
