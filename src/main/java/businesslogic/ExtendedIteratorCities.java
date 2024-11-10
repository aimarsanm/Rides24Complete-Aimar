package businesslogic;

import java.util.List;

public class ExtendedIteratorCities implements ExtendedIterator<String> {
    private List<String> cities;
    private int position;

    public ExtendedIteratorCities(List<String> cities) {
        this.cities = cities;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < cities.size();
    }

    @Override
    public String next() {
        return cities.get(position++);
    }

    @Override
    public String previous() {
        return cities.get(--position);
    }

    @Override
    public boolean hasPrevious() {
        return position > 0;
    }

    @Override
    public void goFirst() {
        position = 0;
    }

    @Override
    public void goLast() {
        position = cities.size() ;
    }
}