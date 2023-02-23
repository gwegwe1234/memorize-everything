.PHONY: build-front-nas build-back-nas docker-compose-nas build-front-local build-back-local podman-compose-local deploy-nas deploy-local

build-front-nas: 
				docker build -t memorize-everything-front:0.1 -f ../front/Dockerfile-front --no-cache

build-back-nas: 
				docker build -t memorize-everything-back:0.1 -f ../back/Dockerfile-back --no-cache

docker-compose-nas: 
				docker-compose up -d

build-front-local: 
				podman build -t memorize-everything-front:0.1 -f ../front/Dockerfile-front --no-cache

build-back-local: 
				podman build -t memorize-everything-back:0.1 -f ../back/Dockerfile-back --no-cache

podman-compose-local: 
				podman-compose up -d

deploy-nas:		build-front-nas build-back-nas docker-compose-nas

deploy-local:	build-front-local build-back-local podman-compose-local