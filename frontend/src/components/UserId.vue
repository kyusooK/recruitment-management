<template>
    <v-card outlined @click="openDialog">
        <v-card-title>
            User : {{ referenceValue ? referenceValue._links.self.href.split('/').pop() : '' }}
        </v-card-title>

        <v-dialog v-model="pickerDialog" width="500">
            <v-card>
                <v-card-title>User</v-card-title>
                <v-card-text>
                    <UserPicker v-model="value" @selected="pick"/>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="pickerDialog = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    import UserPicker from './listers/UserPicker'; 

    export default {
        name: 'UserId',
        components:{
            UserPicker
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
            newValue: {},
            pickerDialog: false,
            referenceValue: null,
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    'id': '',
                }
            }
            else {
                this.newValue = this.value;
                var path = '/users';
                var temp = await axios.get(axios.fixUrl(path + '/' +  Object.values(this.value)[0]));
                if(temp.data) {
                    this.referenceValue = temp.data
                }
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },

        methods: {
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
            openDialog() {
                var path = '/users/';

                if(this.editMode) {
                    this.pickerDialog = true;
                } else {
                    this.pickerDialog = false;
                    this.$router.push(path + this.value.userId);
                }
            },
            async pick(val){
                this.newValue = val;
                var path = '/users';
                var temp = await axios.get(axios.fixUrl(path + '/' + val.userId));
                if(temp.data) {
                    this.referenceValue = temp.data;
                }
                this.referenceValue.nameField = val.nameField;
            },
        }
    }
</script>

