package com.example.meupesohoje.modules.historicScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.meupesohoje.data.model.PersonDataEntity;
import com.example.meupesohoje.domain.PersonDataRepository;

import java.util.List;

public class HistoricViewModel extends ViewModel {

    private PersonDataRepository repository;
    public MutableLiveData<List<PersonDataEntity>> workList = new MutableLiveData<>();
    public MutableLiveData<PersonDataEntity> lastPersonData = new MutableLiveData<>();
    public HistoricViewModel(PersonDataRepository repository){

        this.repository = repository;
    }

    public void getAllPersonData(){
        repository.getAllPersonData().subscribe(personDataEntities -> {
            lastPersonData.postValue(getLastWeigth(personDataEntities));
            workList.postValue(personDataEntities);
        });
    }

    public void deletePersonData(PersonDataEntity personDataEntity){
        repository.deletePersonData(personDataEntity);
    }

    private PersonDataEntity getLastWeigth(List<PersonDataEntity> list){
        return list.get(0);

    }


    static class HistoricViewModelFactory implements ViewModelProvider.Factory{

        private PersonDataRepository repository;

        public HistoricViewModelFactory(PersonDataRepository repository){
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new HistoricViewModel(repository);
        }
    }
}
