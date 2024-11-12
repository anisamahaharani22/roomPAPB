/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow//impor Flow dari kotlinx.coroutines untuk mendukung aliran data secara asinkron

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {//mendefinisikan kelas repository yang berinteraksi dengan database melalui itemDao

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()//mengambil aliran data semua item dari database

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)//mengambil aliran data satu item berdasarkan id dari database

    override suspend fun insertItem(item: Item) = itemDao.insert(item)//menyisipkan item baru ke database

    override suspend fun deleteItem(item: Item) = itemDao.delete(item)//menghapus item dari database

    override suspend fun updateItem(item: Item) = itemDao.update(item)//memperbarui data item di database
}
