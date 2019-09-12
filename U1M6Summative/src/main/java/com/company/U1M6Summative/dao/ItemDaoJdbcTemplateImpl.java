package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {

    private final JdbcTemplate sql;

    @Autowired
    public ItemDaoJdbcTemplateImpl(JdbcTemplate sql) {
        this.sql = sql;
    }

    private String INSERT_ITEM_SQL =
            "INSERT INTO item (name, description, daily_rate)" +
                    "VALUES(?,?,?)";
    private String SELECT_ITEM_SQL =
            "SELECT * FROM item WHERE item_id = ?";
    private String UPDATE_ITEM_SQL =
            "UPDATE item SET name = ?, description = ?, daily_rate = ? WHERE item_id = ?";
    private String DELETE_ITEM_SQL =
            "DELETE FROM item WHERE item_id = ?";
    private String DELETE_INVOICE_ITEM_BY_ITEM_SQL =
            "DELETE FROM invoice_item WHERE invoice_item.item_id = ?";
    private String GET_ALL_ITEM_SQL =
            "SELECT * FROM item";
    private String GET_ALL_ITEM_BY_INVOICE_ITEM_SQL =
            "SELECT * FROM item " +
                    "INNER JOIN invoice_item on invoice_item.item_id = item.item_id";

    @Override
    @Transactional
    public Item saveItem(Item item) {

        sql.update(INSERT_ITEM_SQL, item.getName(), item.getDescription(), item.getDailyRate());
        item.setItemId(sql.queryForObject("select last_insert_id()", Integer.class));
        return item;

    }

    @Override
    public Item findOne(int id) {
        try {
            return sql.queryForObject(SELECT_ITEM_SQL, this::mapItemToRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateItem(Item item) {
        sql.update(UPDATE_ITEM_SQL, item.getName(), item.getDescription(), item.getDailyRate(), item.getItemId());
    }

    @Override
    @Transactional
    public void deleteItem(int id) {
        sql.update(DELETE_INVOICE_ITEM_BY_ITEM_SQL, id);
        sql.update(DELETE_ITEM_SQL, id);
    }

    @Override
    public List<Item> findAll() {
        return sql.query(GET_ALL_ITEM_SQL, this::mapItemToRow);
    }

    @Override
    public List<Item> findAllByInvoiceItem(int id) {
        return sql.query(GET_ALL_ITEM_BY_INVOICE_ITEM_SQL, this::mapItemToRow, id);
    }

    private Item mapItemToRow(ResultSet set, int rowNumber) throws SQLException {

        List<String> columnNames = new ArrayList<>();

        for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
            columnNames.add(set.getMetaData().getColumnName(i));
        }

        columnNames = columnNames.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        List<Method> setters = Arrays.stream(Item.class.getMethods()).filter(method -> method.getName().contains("set")).
                sorted(Comparator.comparing(Method::getName)).collect(Collectors.toList());

        Item item = new Item();

        for (int i = 0; i < setters.size(); i++) {
            try {
                if (setters.get(i).getParameterTypes()[0].getSimpleName().equalsIgnoreCase("localdate")) {
                    setters.get(i).invoke(item, set.getDate(columnNames.get(i)).toLocalDate());
                } else {
                    setters.get(i).invoke(item, set.getObject(columnNames.get(i)));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.err.println("Error in reflective method call");
            }
        }

        return item;

    }
}
