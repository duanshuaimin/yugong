package com.taobao.yugong.translator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.taobao.yugong.common.db.meta.ColumnMeta;
import com.taobao.yugong.common.db.meta.ColumnValue;
import com.taobao.yugong.common.model.record.Record;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;
import java.util.List;

public class UserCenterShardingTranslatorTest {

  @Test
  public void translator() throws Exception {
    UserCenterShardingTranslator translator = new UserCenterShardingTranslator();
    translator.setShardingKeyName("id");

    ColumnMeta columnId = new ColumnMeta("id", Types.INTEGER);
    ColumnMeta columnName = new ColumnMeta("name", Types.VARCHAR);
    List<ColumnValue> primaryKeys = Lists.newArrayList(
        new ColumnValue(columnId, 67));
    List<ColumnValue> columns = Lists.newArrayList(
        new ColumnValue(columnName, "Apple"));
    Record record = new Record();
    record.setTableName("User");
    record.setPrimaryKeys(primaryKeys);
    record.setColumns(columns);

    List<Record> records = translator.translator(ImmutableList.of(record));
    Assert.assertEquals(1, records.size());
    Assert.assertEquals("User_3", records.get(0).getTableName());
    
  }

}