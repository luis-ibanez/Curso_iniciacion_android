package com.geekshubs.recyclerexample.ds.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geekshubs.recyclerexample.ds.model.Product;

import java.util.LinkedList;
import java.util.List;

public class DbSqliteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "ProductDB";
    private static final String TABLE_PRODUCT = "product";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_QUANTITY = "quantity";

    private static final String[] COLUMNS = { PRODUCT_ID, PRODUCT_NAME, PRODUCT_QUANTITY };

    public DbSqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateProductTable = "CREATE TABLE "+TABLE_PRODUCT
                + " ( " + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_NAME+ " TEXT, "
                + PRODUCT_QUANTITY+" INTEGER )";
        db.execSQL(CreateProductTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCT);
        this.onCreate(db);
    }

    public void createProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_QUANTITY, product.getQuantity());

        db.insert(TABLE_PRODUCT, null, values);

        db.close();
    }

    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCT,
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        int productId = Integer.parseInt(cursor.getString(0));
        String productName = cursor.getString(1);
        int productQuantity = Integer.parseInt(cursor.getString(2));

        Product product = new Product(productId, productName, productQuantity);

        return product;
    }

    public List<Product> getAllProducts() {
        List products = new LinkedList();

        String query = "SELECT  * FROM " + TABLE_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Product product = null;
        if (cursor.moveToFirst()) {
            do {
                int productId = Integer.parseInt(cursor.getString(0));
                String productName = cursor.getString(1);
                int productQuantity = Integer.parseInt(cursor.getString(2));

                product = new Product(productId, productName, productQuantity);

                products.add(product);
            } while (cursor.moveToNext());
        }
        return products;
    }

    public int updateProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_QUANTITY, product.getQuantity());

        // update
        int i = db.update(TABLE_PRODUCT, values, PRODUCT_ID + " = ?", new String[] { String.valueOf(product.getId()) });

        db.close();
        return i;
    }

    public void deleteProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCT, PRODUCT_ID + " = ?", new String[] { String.valueOf(product.getId()) });
        db.close();
    }
}

