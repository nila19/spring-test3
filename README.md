# Build

## Build Image
- `gradlew bootBuildImage`

## Docker Publish
- `docker login`
- `docker tag spring-test3:0.0.1 balavigneswaran/spring-test3:snapshot`
- `kubectl set image deployments/spring-test3 spring-test3=balavigneswaran/spring-test3`

- `docker push balavigneswaran/spring-test3`

## K8S ConfigMap
- `kubectl create configmap spring-test3-config --from-file=env=props/env.properties --from-file=env2=props/env2.properties`
- `kubectl create configmap spring-test3-config2 --from-literal=region.cd="PFIX2" --from-literal=dialect="db2-dialect"`
- `kubectl get configmaps spring-test3-config -o yaml`

## Deploy to K8S
- `kubectl apply -f kube/deployment.yaml`
- `kubectl apply -f ./kube`

## TABS
- Tab 1 - minikube
    - `minikube start`
- Tab 2 - dashboard
    - `minikube dashboard`
- Tab 3 - svc/spring-test3
    - `kubectl port-forward svc/spring-test3 9090:8080`
- Tab 4 - svc/name
    - `kubectl port-forward svc/k8s-workshop-name-service 9091:80`
- Tab 5 - pods
    - `kubectl get pods`
    - `kubectl delete pod --selector app=spring-test3`
- # Tab 6 - config
    - `C:\Java\GitHub\spring\spring-test3>kubectl create configmap spring-test3-prop --from-file=kube/config/yaml/application-sit2.yaml --from-file=kube/config/yaml/application-sit3.yaml --from-file=kube/config/yaml/application-sit4.yaml`
    - `C:\Java\GitHub\spring\spring-test3>kubectl delete configmap spring-test3-prop`
- Tab 7 - config2
    - # `C:\Java\GitHub\spring\spring-test3>kubectl apply -f ./kube`
    - `C:\Java\GitHub\spring\spring-test3>kustomize build ./kube | kubectl apply -f -`
- Tab 8 - log
    - `kubectl get pods`
    - `kubectl logs spring-test3-5d68496c4d-kf6sv`
    - `kubectl logs --selector app=spring-test3 -n=my-spring-app -f`
- Tab - exec
    - `kubectl exec -t -i spring-test3-7c48cbdbfd-5h94v -n=my-spring-app bash`
- Tab 9 - build
    - `C:\Java\GitHub\spring\spring-test3>gradlew bootBuildImage`
    - `C:\Java\GitHub\spring\spring-test3>docker tag spring-test3:0.0.1 balavigneswaran/spring-test3:latest`
    - `C:\Java\GitHub\spring\spring-test3>kubectl set image deployments/spring-test3 spring-test3=balavigneswaran/spring-test3:latest`
    - `C:\Java\GitHub\spring\spring-test3>docker push balavigneswaran/spring-test3:latest`
- Tab 10 - Skaffold
    - 'C:\Java\GitHub\spring\spring-test3\kube>skaffold dev --port-forward'
