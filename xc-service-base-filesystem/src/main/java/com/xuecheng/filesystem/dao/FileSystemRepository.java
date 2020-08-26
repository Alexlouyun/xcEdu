package com.xuecheng.filesystem.dao;

import com.xuecheng.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexlou on 2020/6/27.
 */
@Repository
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {
}
