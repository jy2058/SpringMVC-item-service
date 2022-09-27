package hello.itemservice.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item itemA = new Item("itemA", 1000, 10);

        //when
        Item saveItem = itemRepository.save(itemA);

        //then
        Item findItem = itemRepository.findById(saveItem.getId());

        assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("item1", 2000, 20);
        Item item2 = new Item("item2", 4000, 50);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem(){
        //given
        Item item1 = new Item("item1", 2000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 1000, 19);
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findById(itemId);

        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }
}