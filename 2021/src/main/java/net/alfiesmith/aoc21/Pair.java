package net.alfiesmith.aoc21;

import java.util.Objects;

public class Pair<K, V> {

  public K key;
  public V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(this.key, pair.key) && Objects.equals(this.value, pair.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.key, this.value);
  }
}