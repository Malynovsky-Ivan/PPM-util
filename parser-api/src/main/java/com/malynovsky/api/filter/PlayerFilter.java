package com.malynovsky.api.filter;

import com.malynovsky.api.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlayerFilter implements Predicate<Player> {

    private List<Predicate<Player>> predicates;

    private PlayerFilter() {
        predicates = new ArrayList<>();
    }

    @Override
    public boolean test(Player player) {
        return predicates.stream().filter(playerPredicate -> playerPredicate.negate().test(player)).findAny().isEmpty();
    }

    public void addCondition(Predicate<Player> playerPredicate) {
        predicates.add(playerPredicate);
    }

    @Override
    public String toString() {
        return "PlayerFilter{" +
                "predicates=" + predicates +
                '}';
    }

    public static class FilterBuilder {
        private PlayerFilter filter;

        public FilterBuilder() {
            this.filter = new PlayerFilter();
        }

        public FilterBuilder maxAge(int maxAge) {
            if (maxAge > 14) {
                filter.addCondition(player -> player.getAge() <= maxAge);
            }

            return this;
        }

        public FilterBuilder minAge(int maxAge) {
            if (maxAge > 14) {
                filter.addCondition(player -> player.getAge() >= maxAge);
            }

            return this;
        }

        public FilterBuilder country(String countryName) {
            if (countryName != null && !countryName.isEmpty()) {
                filter.addCondition(player -> player.getCountry().equalsIgnoreCase(countryName));
            }

            return this;
        }

        public PlayerFilter build() {
            return filter;
        }
    }
}
