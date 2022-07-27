package com.timothy.iline.data.local_storage.room.daos;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.timothy.iline.domain.modal.Message;
import com.timothy.iline.domain.modal.MessageStatus;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class message_dao_Impl implements message_dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter<Message> __deletionAdapterOfMessage;

  public message_dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `message_table` (`id`,`sender`,`receiver`,`body`,`timeStamp`,`image_url`,`status`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getSender() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSender());
        }
        if (value.getReceiver() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getReceiver());
        }
        if (value.getBody() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBody());
        }
        stmt.bindLong(5, value.getTimeStamp());
        if (value.getImage_url() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getImage_url());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, __MessageStatus_enumToString(value.getStatus()));
        }
      }
    };
    this.__deletionAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `message_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insertMessage(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteMessage(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMessage.handle(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Message> getAllMessage() {
    final String _sql = "SELECT * FROM message_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfReceiver = CursorUtil.getColumnIndexOrThrow(_cursor, "receiver");
      final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        final String _tmpReceiver;
        if (_cursor.isNull(_cursorIndexOfReceiver)) {
          _tmpReceiver = null;
        } else {
          _tmpReceiver = _cursor.getString(_cursorIndexOfReceiver);
        }
        final String _tmpBody;
        if (_cursor.isNull(_cursorIndexOfBody)) {
          _tmpBody = null;
        } else {
          _tmpBody = _cursor.getString(_cursorIndexOfBody);
        }
        final long _tmpTimeStamp;
        _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        final String _tmpImage_url;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImage_url = null;
        } else {
          _tmpImage_url = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final MessageStatus _tmpStatus;
        _tmpStatus = __MessageStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        _item = new Message(_tmpId,_tmpSender,_tmpReceiver,_tmpBody,_tmpTimeStamp,_tmpImage_url,_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __MessageStatus_enumToString(final MessageStatus _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case Received: return "Received";
      case Sent: return "Sent";
      case Draft: return "Draft";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private MessageStatus __MessageStatus_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "Received": return MessageStatus.Received;
      case "Sent": return MessageStatus.Sent;
      case "Draft": return MessageStatus.Draft;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
