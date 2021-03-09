package com.malynovsky.restapp.service.impl;

import com.malynovsky.restapp.entity.Player;
import com.malynovsky.restapp.repository.PlayerRepository;
import com.malynovsky.restapp.service.AbstractService;
import com.malynovsky.restapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl extends AbstractService<Player> implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    protected JpaRepository<Player, Integer> getRepo() {
        return repository;
    }

    @Override
    public List<Player> getAllByAge(Integer min, Integer max) {
        return repository.getAllByAgeBetween(min == null ? 15 : min, max == null ? 19 : max);
    }

    @Override
    public List<Player> getAllTop() { //TODO
        return repository.findAll(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "overall"))).getContent();
    }
}
