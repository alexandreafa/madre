package br.com.basis.madre.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Pacientes.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private Integer elasticSearchFuzzyParameter;

    public Integer getElasticSearchFuzzyParameter() {
        return elasticSearchFuzzyParameter;
    }
    public void setElasticSearchFuzzyParameter(Integer newParameter){
        this.elasticSearchFuzzyParameter = newParameter;
    }
}

