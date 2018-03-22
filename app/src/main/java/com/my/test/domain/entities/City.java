package com.my.test.domain.entities;

/**
 *
 */
public class City {
    private Integer id;
    private String name;
    private String country;

    private City() {}

    public class Builder {
        private Builder() {}

        public Builder setId(Integer id) {
            City.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            City.this.name = name;
            return this;
        }

        public Builder setCountry(String country) {
            City.this.country = country;
            return this;
        }

        public City build() {
            return City.this;
        }
    }

    public static Builder builder() {
        return new City().new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
