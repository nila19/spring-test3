echo ">>> Build started..."
gradlew bootBuildImage
echo ">>> Build is completed..."

docker login
docker tag spring-test3:0.0.1 balavigneswaran/spring-test3
docker push balavigneswaran/spring-test3
echo ">>> Pushed image to docker..."

kubectl apply -f kube/deployment.yaml
echo ">>> Redeployed to k8s..."
