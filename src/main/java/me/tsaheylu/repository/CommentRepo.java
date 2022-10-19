package me.tsaheylu.repository;

import me.tsaheylu.dto.CommentDTO;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.Comment;
import me.tsaheylu.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {


    @Query("        SELECT new me.tsaheylu.dto.CommentDTO(m,a) " +
            "      from Comment as m" +
            "      left join User as a on m.fromid = a.id " +
            "      where m.id = :id " +
            "      order by m.sendtime desc")
    CommentDTO getDtoById(Long id);

    @Query("        SELECT new me.tsaheylu.dto.CommentDTO(m,a) " +
            "      from Comment as m" +
            "      left join User as a on m.fromid = a.id " +
            "      where m.id in :idlist " +
            "      order by m.sendtime desc")
    List<CommentDTO> getDtoListById(List<Long> idlist);

}

