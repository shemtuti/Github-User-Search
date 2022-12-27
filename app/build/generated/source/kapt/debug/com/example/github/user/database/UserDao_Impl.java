package com.example.github.user.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DatabaseUser> __insertionAdapterOfDatabaseUser;

  private final EntityInsertionAdapter<DatabaseFollower> __insertionAdapterOfDatabaseFollower;

  private final EntityInsertionAdapter<DatabaseFollowing> __insertionAdapterOfDatabaseFollowing;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFollower;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFollowing;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDatabaseUser = new EntityInsertionAdapter<DatabaseUser>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `user` (`entryId`,`login`,`id`,`name`,`email`,`bio`,`avatar_url`,`location`,`public_repos`,`followers`,`following`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabaseUser value) {
        stmt.bindLong(1, value.getEntryId());
        if (value.getLogin() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLogin());
        }
        stmt.bindLong(3, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEmail());
        }
        if (value.getBio() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getBio());
        }
        if (value.getAvatar_url() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAvatar_url());
        }
        if (value.getLocation() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLocation());
        }
        stmt.bindLong(9, value.getPublic_repos());
        stmt.bindLong(10, value.getFollowers());
        stmt.bindLong(11, value.getFollowing());
      }
    };
    this.__insertionAdapterOfDatabaseFollower = new EntityInsertionAdapter<DatabaseFollower>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `follower` (`login`,`avatar_url`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabaseFollower value) {
        if (value.getLogin() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getLogin());
        }
        if (value.getAvatar_url() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAvatar_url());
        }
      }
    };
    this.__insertionAdapterOfDatabaseFollowing = new EntityInsertionAdapter<DatabaseFollowing>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `following` (`login`,`avatar_url`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabaseFollowing value) {
        if (value.getLogin() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getLogin());
        }
        if (value.getAvatar_url() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAvatar_url());
        }
      }
    };
    this.__preparedStmtOfDeleteFollower = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM follower";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFollowing = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM following";
        return _query;
      }
    };
  }

  @Override
  public void insertUser(final DatabaseUser users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDatabaseUser.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertFollowers(final DatabaseFollower... follower) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDatabaseFollower.insert(follower);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertFollowing(final DatabaseFollowing... following) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDatabaseFollowing.insert(following);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteFollower() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFollower.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteFollower.release(_stmt);
    }
  }

  @Override
  public void deleteFollowing() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFollowing.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteFollowing.release(_stmt);
    }
  }

  @Override
  public LiveData<List<DatabaseUser>> getUser() {
    final String _sql = "SELECT * FROM user ORDER BY entryId DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"user"}, false, new Callable<List<DatabaseUser>>() {
      @Override
      public List<DatabaseUser> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEntryId = CursorUtil.getColumnIndexOrThrow(_cursor, "entryId");
          final int _cursorIndexOfLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "login");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfBio = CursorUtil.getColumnIndexOrThrow(_cursor, "bio");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar_url");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfPublicRepos = CursorUtil.getColumnIndexOrThrow(_cursor, "public_repos");
          final int _cursorIndexOfFollowers = CursorUtil.getColumnIndexOrThrow(_cursor, "followers");
          final int _cursorIndexOfFollowing = CursorUtil.getColumnIndexOrThrow(_cursor, "following");
          final List<DatabaseUser> _result = new ArrayList<DatabaseUser>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseUser _item;
            final long _tmpEntryId;
            _tmpEntryId = _cursor.getLong(_cursorIndexOfEntryId);
            final String _tmpLogin;
            if (_cursor.isNull(_cursorIndexOfLogin)) {
              _tmpLogin = null;
            } else {
              _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpBio;
            if (_cursor.isNull(_cursorIndexOfBio)) {
              _tmpBio = null;
            } else {
              _tmpBio = _cursor.getString(_cursorIndexOfBio);
            }
            final String _tmpAvatar_url;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatar_url = null;
            } else {
              _tmpAvatar_url = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final int _tmpPublic_repos;
            _tmpPublic_repos = _cursor.getInt(_cursorIndexOfPublicRepos);
            final int _tmpFollowers;
            _tmpFollowers = _cursor.getInt(_cursorIndexOfFollowers);
            final int _tmpFollowing;
            _tmpFollowing = _cursor.getInt(_cursorIndexOfFollowing);
            _item = new DatabaseUser(_tmpEntryId,_tmpLogin,_tmpId,_tmpName,_tmpEmail,_tmpBio,_tmpAvatar_url,_tmpLocation,_tmpPublic_repos,_tmpFollowers,_tmpFollowing);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DatabaseFollower>> getFollowers() {
    final String _sql = "SELECT * FROM follower";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"follower"}, false, new Callable<List<DatabaseFollower>>() {
      @Override
      public List<DatabaseFollower> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "login");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar_url");
          final List<DatabaseFollower> _result = new ArrayList<DatabaseFollower>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseFollower _item;
            final String _tmpLogin;
            if (_cursor.isNull(_cursorIndexOfLogin)) {
              _tmpLogin = null;
            } else {
              _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
            }
            final String _tmpAvatar_url;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatar_url = null;
            } else {
              _tmpAvatar_url = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            _item = new DatabaseFollower(_tmpLogin,_tmpAvatar_url);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DatabaseFollowing>> getFollowing() {
    final String _sql = "SELECT * FROM following";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"following"}, false, new Callable<List<DatabaseFollowing>>() {
      @Override
      public List<DatabaseFollowing> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "login");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar_url");
          final List<DatabaseFollowing> _result = new ArrayList<DatabaseFollowing>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseFollowing _item;
            final String _tmpLogin;
            if (_cursor.isNull(_cursorIndexOfLogin)) {
              _tmpLogin = null;
            } else {
              _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
            }
            final String _tmpAvatar_url;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatar_url = null;
            } else {
              _tmpAvatar_url = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            _item = new DatabaseFollowing(_tmpLogin,_tmpAvatar_url);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
