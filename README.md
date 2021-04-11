# ForgeRocks

Minecraft Forge mod containing the JNI bindings for [rocksdb](https://github.com/facebook/rocksdb/).

This is a standalone mod in order to avoid having every single mod that uses it have to shade it in, because rocksdb
is **big** (> 30MiB).

All copyrights belong to the rocksdb developers.

## Usage

```groovy
repositories {
    maven {
        url = "https://maven.daporkchop.net/"
    }
}

dependencies {
    compile "net.daporkchop:forgerocks:FORGEROCKS_VERSION"
    //see below for list of versions
}
```

<table>
<thead>
<tr>
<th>Minecraft version</th>
<th>rocksdb version</th>
<th>ForgeRocks version</th>
</tr>
</thead>
<tbody>
<tr>
<td>1.12.2</td>
<td>6.13.3</td>
<td>6.13.3-1.12.2</td>
</tr>
</tbody>
</table>