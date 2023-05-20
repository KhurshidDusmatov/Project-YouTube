select * from playlist
select * from channel
select * from attach
select * from video
select * from profile
select * from category
select * from playlist_video

insert into playlist_video(id, playlist_id, video_id, created_date)
values(5, 5, 3, now())

update playlist_video set created_date = now()

    insert into playlist(name, description, status, channel_id, visible, order_num, created_date)
values('P Lessons', 'AboutP', 'PUBLIC', '1', true, 3, now())


insert into channel(id, name,description, status, profile_id, banner_id, photo_id)
values('1', 'Java', 'About Java', 'ACTIVE', 1, '1', '1' )

insert into attach(id, extension, path, size, created_date, original_name)
values(2,'ext1', 'path', 12, now(), 'name123')

insert into category(id, name, created_date)
values('c1', 'muz', now())

insert into video(id, preview_attach_id, title,category_id,attach_id,created_date,published_date,
                  status, type,view_count,shared_count,description,channel_id,like_count,dislike_count)
values(3, '1', 'title', 'c1', '1', now(), now(), 'PUBLIC', 'SHORT', 1,1,'desc', '1', 1, 2)

select count(video_id) from playlist_video where playlist_id = 4

select created_date from playlist_video where playlist_id = 5  order by created_date desc limit 1
select video_id from playlist_video where playlist_id = 4

