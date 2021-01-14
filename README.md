# Voucherstore

Vourcher store is a store made during PP5 programming.

## Description

Use Command line to make API requests to deployed store 

```bash
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kuba", "lastname": "Zalucki", "address": {"street": "mickiewicza"}}'
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kuba", "lastname": "Zalucki", "address": {"street": "mickiewicza"}}'
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kuba", "lastname": "Zalucki", "address": {"street": "mickiewicza"}}'

```
 
 ### CI

 Ci is done in github actions, but a pipeline is also configured for GitlabCI


### **Milestone1**

- [x] Zarządzanie listą produktów z poziomu serwisu
- [x] Ekspozycja listy produktów via HTTP endpoint
- [x] Testy funkcjonalne i jednostkowe
- [x] Automatyzacja budowania projektu z wykorzystanie narzędzia CI (github actions)

## Authors

* **kubino148** 
