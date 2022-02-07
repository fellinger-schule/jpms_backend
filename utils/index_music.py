import os
import taglib
import mariadb as database

basedir = "D:\\Shared"

connection = database.connect(
    user="root",
    password="root",
    host="192.168.88.100",
    db="db")

cur = connection.cursor()

def newId():
    cur.execute("SELECT NEXT VALUE FOR hibernate_sequence")
    return (cur.fetchall()[0][0])

for root, dirs, files in os.walk(basedir):
    for file in files:
        if file.endswith(".flac") or file.endswith(".mp3"):
            path = root[10:]
            fullpath = path.replace("\\", "/")+"/"+file
            diskpath = root+"\\"+file

            f = taglib.File(diskpath)

            try:
                albumTitle = f.tags["ALBUM"][0]
                albumArtist = f.tags["ALBUMARTIST"][0]
                trackTitle = f.tags["TITLE"][0]
                trackNo = f.tags["TRACKNUMBER"][0]
                trackLength = f.length
                trackMimeType = "audio/mpeg" if file[-3:] == "mp3" else "audio/flac"

                if(not trackNo.isnumeric()):
                    continue

                # Get Artist ID
                cur.execute("SELECT COUNT(*) FROM Artist WHERE name=?", (albumArtist,))
                artistExists = (cur.fetchall()[0][0] != 0)
                if(not artistExists):
                    cur.execute("INSERT INTO Artist (id, name) VALUES (%s, %s)", (str(newId()), albumArtist))
                cur.execute("SELECT id FROM Artist WHERE name=?", (albumArtist,))
                artistId = cur.fetchall()[0][0]

                # Get Album Id
                cur.execute("SELECT COUNT(*) FROM Album WHERE title=?", (albumTitle,))
                albumExists = (cur.fetchall()[0][0] != 0)
                if(not albumExists):
                    cur.execute("INSERT INTO Album (id, title, artist_id) VALUES (%s, %s, %s)", (str(newId()), albumTitle, artistId))
                cur.execute("SELECT id FROM Album WHERE title=?", (albumTitle,))
                albumId = cur.fetchall()[0][0]

                # See if track already exists
                cur.execute("""SELECT COUNT(*) 
                               FROM Track 
                               WHERE trackno=? 
                               AND album_id=?""", 
                               (trackNo, albumId,))
                if(cur.fetchall()[0][0] == 0):
                    print(f"Inserting {trackNo}: {trackTitle}")
                    cur.execute("""INSERT INTO Track (id, album_id, artist_id, length, mimeType, path, title, trackno)
                                   VALUES (%s, %s, %s, %s, %s, %s, %s, %s)""",
                                   (str(newId()), albumId, artistId, trackLength,trackMimeType, fullpath, trackTitle, trackNo))
                    connection.commit()

                #cur.execute("INSERT INTO Artist (id, name) VALUES (%s, %s)", (str(newId()), "Maria"))
                #connection.commit() 

            except KeyError:
                pass
            except IndexError:
                print("Index error")
            except database.DataError as e:
                print(e)


            f.close()
            